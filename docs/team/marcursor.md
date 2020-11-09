# Marcus Tan's Project Portfolio Page

## Project: Scheduler--;

Scheduler--; (S--) is a desktop daily planner application for managing day-to-day events. The user interacts with it using a Command Line Interface (CLI). It is written in Java.

Given below are my contributions to this project.

### Summary of Contributions

- Feature: Implemented the ability to check for events occurring within a given time period.
    - What it does: Allows the user to provide a start date, start time, end date and end time to specify a time period to check for events.
    - Justification: This feature is useful because it enables users to easily check their availability during a given period.
    - Highlights: This feature allows for various input formats for date and time, including partially filled out dates/times. This required analysis on how to process date and time inputs to return a valid date/time value.
    
- Feature: Implemented the mark events as done/undone.
    - What it does: Allows the user to set the status of events to done/undone.
    - Justification: This feature is important as it allows users to keep track of the status of their events.
    - Highlights: This feature needs to look for matching events in sub ArrayLists in the case of repeated events. This involves interpreting of the events during execution. 
    
- Feature: Implemented the ability to delete events from Scheduler--;.
    - What it does: Allows the user to delete events from Scheduler--;.
    - Justification: This feature is important as it allows users to have more control over their events schedule. The feature allows for removal of obsolete or erroneously input events.
    - Highlights: 
    
- Code contributed: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=marcursor&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=marcursor&tabRepo=AY2021S1-CS2113T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

- Project Management: 
    - Managed releases `v1.0`, `v2.0` and `v2.1` (3 releases) on Github

- Documentation:
    - User Guide:
        - Added documentation for the `check`, `done`, `undone` and `delete` features
    - Developer Guide:
        - Added design details of the `Parser` and `UserData` components.
        - Added implementation details of the `check` feature.

- Community:
    - Tested and reviewed other groups project (with non-trivial review comments): [PR Review](https://github.com/marcursor/ped/issues)
    - DG reviewed (with non-trivial review comments): [DG Review](https://github.com/nus-cs2113-AY2021S1/tp/pull/4#pullrequestreview-519320304)
