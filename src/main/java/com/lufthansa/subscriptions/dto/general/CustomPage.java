package com.lufthansa.subscriptions.dto.general;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomPage<T> {

    private int currentPage;
    private int totalPages;
    private long totalElements;
    private List<T> data;

    public CustomPage(Page<T> page) {
        this.data = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }

    public CustomPage(int pageNumber, int pageSize, List<T> data) {
        if (data == null) data = Collections.emptyList();
        if (pageSize <= 0) throw new IllegalArgumentException("pageSize must be > 0");

        this.totalElements = data.size();
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
        this.currentPage = pageNumber;

        int skip = pageNumber * pageSize;
        this.data = data.stream()
                .skip(skip)
                .limit(pageSize)
                .toList();
    }

    public static <T> CustomPage<T> empty() {
        CustomPage<T> customPage = new CustomPage<>();
        customPage.setCurrentPage(0);
        customPage.setTotalElements(0);
        customPage.setTotalPages(0);
        customPage.setData(Collections.emptyList());
        return customPage;
    }

    public static <T> CustomPage<T> of(int currentPage, int totalPages, long totalElements, List<T> data) {
        CustomPage<T> customPage = new CustomPage<>();
        customPage.setCurrentPage(currentPage);
        customPage.setTotalElements(totalElements);
        customPage.setTotalPages(totalPages);
        customPage.setData(data);
        return customPage;
    }
}