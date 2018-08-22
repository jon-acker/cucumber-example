Feature:

  Rules:
  - Borrower needs to be registered with the library
  - Library has a catalog which says how many of each book is in stock
  - You can only borrow a book that is in stock
  - Only 2 books at a time
  - Books can be borrowed for 3 days
  - returning a book up to a week late incurs £5 fine
  - returning a book more than a week late incurs £10 fine
  - books can be checked out or renewed at the Circulation Desk
  - a book can only be renewed once
  - Borrower can reserve a book that's not in stock
  in which case a hold is placed on the book and as soon
  as it is returned the borrower is notified and no one else can
  borrow the book.

  Background:
    Given Jon is a member of the library

  Scenario: Borrower is lent a book that's in stock
    Given there is 1 copy of "Harry Potter" in stock
    When Jon borrows the book "Harry Potter" from the library
    Then stock count for "Harry Potter" should be 0
    And the book "Harry Potter" should be loaned to Jon

  Scenario: Borrower is declined book because they're not a member
    Given Mark is not a member of the library
    When Mark tries to borrow the book "Harry Potter"
    Then Mark should be told "You must be a registered member to borrow books"

  Scenario: Denied because book is not in stock
    Given there are no copies of "Harry Potter" in stock
    When Jon tries to borrow the book "Harry Potter"
    Then Jon should be told "Sorry, this book is out of stock"

  Scenario: Loan is denied because Jon already has 2 books
    Given Jon has the following books out on loan:
      | TDD For Wizards   |
      | Lord of the Rings |
    When Jon tries to borrow the book "Harry Potter"
    Then Jon should be told "You cannot borrow more than 2 books"

  Scenario: Loan is denied because Jon has unpaid fines
    Given Jon has a £5 unpaid fine
    When Jon tries to borrow the book "Harry Potter"
    Then Jon should be told "you cannot borrow any books because you have unpaid fines"

  Scenario: Returning a book without incurring a fine
    Given Jon borrowed the book "Harry Potter" on 17th March
    When Jon returns the book "Harry Potter" on the 20th of March at 23:59
    Then Jon should not be charged a fine

  Scenario: Returning a book late - incurring a fine (after cuttoff)
    Given Jon borrowed the book "Harry Potter" on 17th March
    When Jon returns the book "Harry Potter" on the 21st of March at 00:00
    Then Jon should be charged a £5 fine

  Scenario: Stock increases back when book is returned
    Given there is 1 copy of "Harry Potter" in stock
    When Jon returns the book "Harry Potter" from the library
    Then stock count for "Harry Potter" should be 2
    And the book "Harry Potter" should not be marked as loaned to Jon
