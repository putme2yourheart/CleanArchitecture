package io.github.putme2yourheart.cleanarchitecture.data.cache.serializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public FileManager() {
    }

    /**
     * 将用户信息文件保存到文件缓存中
     */
    public void writeToFile(File file, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从文件缓存中获取用户信息
     *
     * @return json用户信息
     */
    public String readFile(File file) {
        final StringBuilder fileContentBuilder = new StringBuilder();
        if (file.exists()) {
            String stringLine;
            try {
                final FileReader fileReader = new FileReader(file);
                final BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((stringLine = bufferedReader.readLine()) != null) {
                    fileContentBuilder.append(stringLine);
                }
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContentBuilder.toString();
    }

    // 获取缓存最后的更新时间
    public long readLastCacheUpdateTimeMillis(File file) {
        final StringBuilder fileContentBuilder = new StringBuilder();
        long lastCacheUpdateTimeMillis = 0;
        if (file.exists()) {
            String stringLine;
            try {
                final FileReader fileReader = new FileReader(file);
                final BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((stringLine = bufferedReader.readLine()) != null) {
                    fileContentBuilder.append(stringLine);
                }
                bufferedReader.close();
                fileReader.close();
                lastCacheUpdateTimeMillis = Long.parseLong(fileContentBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lastCacheUpdateTimeMillis;
    }

    // 保存缓存最后的更新时间
    public void writeLastCacheUpdateTimeMillis(File file) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            final long currentTimeMillis = System.currentTimeMillis();
            writer.write(String.valueOf(currentTimeMillis));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断文件是否存在
     *
     * @return true 存在
     */
    public boolean exists(File file) {
        return file.exists();
    }
}
