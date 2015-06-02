package io.paperdb;

import android.content.Context;

import com.esotericsoftware.kryo.Kryo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class DbStorageBase {
    protected Kryo getKryo() {
        return kryos.get();
    }

    private ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.register(PaperTable.class);
            return kryo;
        }
    };

    public abstract void destroy(Context context, String dbName);

    public abstract <E extends Serializable> void insert(String tableName, Collection<E> items);

    public abstract <E extends Serializable> List<E> select(String tableName);

    public abstract boolean exist(String tableName);

    public abstract void deleteIfExists(String tableName);
}
