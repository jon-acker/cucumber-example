Feature:

  Rules:
  - Borrower needs to be registered with the library
  - Library has a catalog which says how many of each book is in stock
  - You can only borrow a book that is in stock
  - Only 2 books at a time
  - Books can be borrowed for 3 days
  - returning a book up to a week late incurs £5 fine
  - returning a book more than a week late uncurs £10 fine
  - books can be checked out or renewed at the Circulation Desk
  - a book can only be renewed once
  - Borrower can reserve a book that's not in stock
  in which case a hold is placed on the book and as soon
  as it is returned the borrower is notified and no one else can
  borrow the book.

  Background:

  Scenario: Borrower is lent a book that's in stock






