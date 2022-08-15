package com.qxy.NoError.list.version;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.qxy.NoError.list.bean.Version;
import com.qxy.NoError.list.model.VersionModel;
import com.qxy.NoError.list.net.ResponseData;

import org.jetbrains.annotations.NotNull;

import cn.hutool.json.JSONUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 分页显示版本信息
 *
 * @author 徐鑫
 */
public class VersionPagingSource extends RxPagingSource<Integer, Version> {
    @NonNull
    private final VersionModel mBackend;
    private final int type;

    private static final String TAG = "VersionPagingSource";

    public VersionPagingSource(@NonNull VersionModel backend, int type) {
        Log.d(TAG, "VersionPagingSource: ");
        mBackend = backend;
        this.type = type;
    }

    @NotNull
    @Override
    public Single<LoadResult<Integer, Version>> loadSingle(
            @NotNull LoadParams<Integer> params) {
        Log.d(TAG, "loadSingle: ");
        // Start refresh at page 1 if undefined.
        Integer nextPageNumber = params.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 1;
        }

        return mBackend.getVersionData(type, nextPageNumber, 20)
                .subscribeOn(Schedulers.io())
                .map(this::toLoadResult)
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, Version> toLoadResult(
            @NonNull ResponseData<Version> response) {
        Log.d(TAG, "toLoadResult: " + JSONUtil.toJsonStr(response));
        Integer nextPage = response.data.hasMore ? response.data.cursor : null;

        return new LoadResult.Page<>(
                response.data.list,
                // Only paging forward.
                null,
                nextPage,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NotNull PagingState<Integer, Version> state) {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, Version> anchorPage = state.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }
}
