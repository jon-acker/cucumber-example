package acme.domain.transforms;

import acme.Book;
import cucumber.api.Transformer;

public class BookTransformer extends Transformer<Book> {

    @Override
    public Book transform(String name) {
        return new Book(name);
    }
}
