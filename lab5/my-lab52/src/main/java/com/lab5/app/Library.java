package com.lab5.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
 
public class Library {
	private final List<Book> store = new ArrayList<>();
 
	public void addBook(final Book book) {
		store.add(book);
	}

    public List<Book> findBooks(final Date from, final Date to) {
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : store) {
            if (isWithinDateRange(book.getPublished(), from, to)) {
                filteredBooks.add(book);
            }
        }

        filteredBooks.sort(new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                return book2.getPublished().compareTo(book1.getPublished());
            }
        });

        return filteredBooks;
    }

    // Helper method to check if a date is within a specified range
    private boolean isWithinDateRange(Date date, Date from, Date to) {
        return date.after(from) && date.before(to);
    }
} 
