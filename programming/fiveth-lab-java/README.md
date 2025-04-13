# Study Group Manager

A console application for managing a collection of study groups.

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Building

```bash
mvn clean package
```

## Running

1. Set the environment variable for the data file:
```bash
export STUDY_GROUP_FILE=/path/to/your/data.json
```

2. Run the application:
```bash
java -jar target/study-group-manager-1.0-SNAPSHOT.jar
```

## Available Commands

- `help` - show help for available commands
- `info` - show collection information
- `show` - show all elements
- `add` - add new element
- `update id` - update element by id
- `remove_by_id id` - remove element by id
- `clear` - clear collection
- `save` - save collection to file
- `execute_script file_name` - execute script from file
- `exit` - exit program
- `insert_at index` - insert element at index
- `remove_first` - remove first element
- `add_if_min` - add element if it's less than minimum
- `group_counting_by_group_admin` - group elements by admin
- `count_by_students_count count` - count elements by students count
- `print_field_ascending_group_admin` - print admin fields in ascending order

## Data Format

The application uses JSON format for storing data. Example:

```json
[
  {
    "id": 1,
    "name": "Group A",
    "coordinates": {
      "x": 10.5,
      "y": 20.0
    },
    "creationDate": "2024-03-15T10:30:00",
    "studentsCount": 25,
    "shouldBeExpelled": 5,
    "averageMark": 4.5,
    "formOfEducation": "FULL_TIME_EDUCATION",
    "groupAdmin": {
      "name": "John Doe",
      "birthday": "1990-01-01",
      "height": 180.5,
      "passportID": "AB123456",
      "eyeColor": "BLUE"
    }
  }
]
```

## Error Handling

The application handles various error cases:
- Invalid input data
- File access errors
- Invalid command format
- Missing environment variable
- JSON parsing errors 