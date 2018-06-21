package com.aion.dashboard.datatransferobject;

import java.util.List;

public class PageableDTO{


    private static class Page{
        private long totalElements;
        private long totalPages;
        private long number;
        private long size;

        private Page(){}
        private Page(long totalElements, long totalPages, long number, long size) {
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.number = number;
            this.size = size;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public Page setTotalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public long getTotalPages() {
            return totalPages;
        }

        public Page setTotalPages(long totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public long getNumber() {
            return number;
        }

        public Page setNumber(long number) {
            this.number = number;
            return this;
        }

        public long getSize() {
            return size;
        }

        public Page setSize(long size) {
            this.size = size;
            return this;
        }
    }

    private List content;
    private Page page;
    public PageableDTO(List content, Page page) {
        this.content = content;
        this.page = page;

    }

    public List getContent() {
        return content;
    }

    public PageableDTO setContent(List content) {
        this.content = content;
        return this;
    }

    public Page getPage() {
        return page;
    }

    public PageableDTO setPage(Page page) {
        this.page = page;
        return this;
    }

    public static PageBuilder getBuilder(){return new PageBuilder<>();}


    public static class PageBuilder<T>{

        private PageBuilder(){}


        private long totalElements;
        private long totalPages;
        private long number;
        private long size;
        private List<T> list;

        public PageBuilder<T> setTotalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public PageBuilder<T> setTotalPages(long totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public PageBuilder<T> setNumber(long number) {
            this.number = number;
            return this;
        }

        public PageBuilder<T> setSize(long size) {
            this.size = size;
            return this;
        }


        public PageBuilder<T> setList(List<T> list) {
            this.list = list;
            return this;
        }

        public PageableDTO build(){
            return new PageableDTO(list, new Page(totalElements,totalPages,number,size));
        }
    }
}
