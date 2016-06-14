/*
 * Copyright 2015 XiNGRZ <chenxingyu92@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xingrz.gankmeizhi.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.xingrz.gankmeizhi.db.Image;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApi {

    @GET("data/%E7%A6%8F%E5%88%A9/{count}/{page}")
    Call<Result<List<Image>>> latest(@Path("count") int count,
                                     @Path("page") int page);

    //@GET("get/{count}/since/{year}/{month}/{day}")
    @GET("day/{year}/{month}/{day}")
    Call<Result<List<String>>> since(@Path("count") int count,
                                     @Path("year") String year,
                                     @Path("month") String month,
                                     @Path("day") String day);

    //@GET("get/{count}/before/{year}/{month}/{day}")
    @GET("day/{year}/{month}/{day}")
    Call<Result<List<String>>> before(@Path("count") int count,
                                      @Path("year") String year,
                                      @Path("month") String month,
                                      @Path("day") String day);

    @GET("day/{year}/{month}/{day}")
    Call<Result<Article>> get(@Path("year") String year,
                              @Path("month") String month,
                              @Path("day") String day);

    @GET("search/query/listview/category/%E7%A6%8F%E5%88%A9/{count}/1")
    Call<Result<List<Image>>> query(@Path("count") int count);

    class Result<T> {

        public boolean error;

        public T results;

    }

    class Article {

        @SerializedName("福利")
        public List<Image> images;

    }

}
