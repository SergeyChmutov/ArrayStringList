package pro.sky.stringlist.services;

import pro.sky.stringlist.exceptions.*;
import pro.sky.stringlist.interfaces.StringList;

public class ArrayStringList implements StringList {
    private final int DEFAULT_STORAGE_CAPACITY = 10;
    private String[] storage;
    private int size;

    public ArrayStringList() {
        this.storage = new String[DEFAULT_STORAGE_CAPACITY];
        this.size = 0;
    }

    public ArrayStringList(int capacity) {
        if (capacity < 0) {
            throw new StringListStorageInitializationException(
                    "Первоначальный размер для хранения значений списка не может быть задан отрицательным числом"
            );
        }
        this.storage = new String[capacity];
        this.size = 0;
    }

    @Override
    public String add(String item) {
        checkParameterForNull(item);
        rearrangeStorageIfNeeded();
        storage[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkIndexValidRangeForAddItem(index);
        checkParameterForNull(item);
        rearrangeStorageIfNeeded();
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndexValidRange(index);
        checkParameterForNull(item);
        storage[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkParameterForNull(item);
        int indexOfItem = indexOf(item);
        if (indexOfItem == -1) {
            throw new StringListItemNotFoundException("Не найден указанный элемент");
        }
        size--;
        if (indexOfItem == 0) {
            storage[size] = null;
        } else {
            System.arraycopy(storage, indexOfItem + 1, storage, indexOfItem, size - indexOfItem);
        }
        return item;
    }

    @Override
    public String remove(int index) {
        checkIndexValidRange(index);
        String item = storage[index];
        size--;
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        storage[size] = null;
        return item;
    }

    @Override
    public boolean contains(String item) {
        checkParameterForNull(item);
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        checkParameterForNull(item);
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return  i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkParameterForNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return  i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndexValidRange(index);
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (this == otherList) {
            return true;
        }
        if (otherList == null || size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!storage[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] result = new String[size];
        System.arraycopy(storage, 0, result, 0, size);
        return result;
    }

    private void checkParameterForNull(String item) {
        if (item == null) {
            throw new StringListNullArgumentValueException("Обрабатываемая строка не может иметь значение null");
        }
    }

    private void rearrangeStorageIfNeeded() {
        if (storage.length == size) {
            rearrangeStorage();
        }
    }

    private void rearrangeStorage() {
        int newStorageLength = (size * 3) / 2 + 1;
        String[] newStorage = new String[newStorageLength];
        System.arraycopy(storage, 0, newStorage, 0, size);
        this.storage = newStorage;
    }

    private void checkIndexValidRangeForAddItem(int index) {
        if (index < 0 || index > size) {
            throw new StringListIndexOutOfBoundsException("Индекс " + index + ", размер списка " + size);
        }
    }

    private void checkIndexValidRange(int index) {
        if (isEmpty() || index < 0 || index > size - 1) {
            throw new StringListIndexOutOfBoundsException("Индекс " + index + ", размер списка " + size);
        }
    }
}
