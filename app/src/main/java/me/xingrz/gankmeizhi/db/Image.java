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

package me.xingrz.gankmeizhi.db;

import android.graphics.Point;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.annotations.PrimaryKey;
import me.xingrz.gankmeizhi.net.ImageFetcher;

public class Image extends RealmObject {

    @PrimaryKey
    @SerializedName("objectId")
    private long _id;

    //@PrimaryKey Only one Primary Key
    private String id;

    private String url;

    private int width;
    private int height;

    private Date createAt;

    private Date publishedAt;

    private String desc;

    private String source;

    // private String type:"福利", used: true, who: "donot care"

    public static RealmResults<Image> all(Realm realm) {
        return realm.where(Image.class)
                .findAllSorted("publishedAt", Sort.DESCENDING);
    }

    public static Image persist(Image image, ImageFetcher imageFetcher)
            throws IOException, InterruptedException, ExecutionException {
        Point size = new Point();

        Realm realm = Realm.getDefaultInstance();

        imageFetcher.prefetchImage(image.getUrl(), size);

        long nextID = realm.isEmpty() ? 0l : (int) (realm.where(Image.class).max("_id").longValue() + 1);

        image.set_id(nextID);

        image.setWidth(size.x);
        image.setHeight(size.y);

        return image;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
