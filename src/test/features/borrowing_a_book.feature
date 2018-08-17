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
    Given there is 1 copy of "TDD for Dummies" in stock
    When Jon borrows the book "TDD for Dummies"
    Then Jon should have been lent "TDD for Dummies"
    And there should be no copies of "TDD for Dummies" in stock

  Scenario:
    Given there are no copies of "TDD for Dummies" in stock
    When Jon tries to borrow the book "TDD for Dummies"
    Then Jon should be told "sorry you can't borrow this book"

  Scenario: Borrower is already at his borrowing limit
    Given Jon has borrowed the following books
      | TDD for Dummies |
      | Advanced TDD    |
    When Jon tries to borrow the book "Super advanced TDD"
    Then Jon should be told "sorry, you are at your borrowing limit"

  Scenario: Returning a book increments stock levels
    Given Jon has been lent the book "Advanced TDD"
    And there are 2 copies of the book "Advanced TDD" left in stock
    When Jon returns the book "Advanced TDD"
    Then there should be 3 copies of "Advance TDD" in stock


  Scenario: Returning a book late incurs a fine
    Given Jon was lent the book "Advanced TDD" on 1st Jun at 12:00
    When Jon returns the book "Advanced TDD" on 4th Jun at 12:00
    Then Jon's account should be charged a £5 fine

  Scenario: Returning a book on time does not incur fine
    Given Jon was lent the book "Advanced TDD" on 1st Jun at 12:00
    When Jon returns the book "Advanced TDD" on 4th Jun at 11:59
    Then Jon's account should not be charged a fine





