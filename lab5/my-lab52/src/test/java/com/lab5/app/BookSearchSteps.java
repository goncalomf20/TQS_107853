package com.lab5.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

 
public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
 
    @Given("a book with the title {string}, written by {string}, published in {int} {int} {int}")
    public void addNewBook(final String title, final String author, final int day, final int month, final int year) {;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day); 
        Date published = calendar.getTime();
        System.out.println(published);

        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    
	@When("the customer searches for books published between {int} and {int}")
	public void setSearchParameters(final int fromYear, final int toYear) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.set(fromYear, 1 - 1, 1); 
        calendar2.set(toYear + 1, 1 - 1, 1); //Até ao fim de toYear
        Date from = calendar.getTime();
        Date to = calendar2.getTime();
        System.out.println(from);
        System.out.println(to);

		result = library.findBooks(from, to);
        System.out.println(result);
	}
 
	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
    
	@Then("Book (\\d+) should have the title '(.+)'$")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}
}