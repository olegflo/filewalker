package com.olegflo.filewalker;

import java.io.File;

/**
 * @author Oleg Soroka
 * @date 29.10.13
 * @time 21:59
 * <p/>
 * Describes item for filewalker list
 */
public class FileItem {

    private File file;
    private String title;

    public FileItem(File file) {
        this.file = file;
        title = file.getName();
    }

    public FileItem(File file, String title) {
        this.file = file;
        this.title = title;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}