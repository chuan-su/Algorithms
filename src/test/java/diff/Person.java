package diff;

import java.util.Objects;

class Person {
  final int id;
  String name;

  Person(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public int hashCode(){
    return Objects.hashCode(id);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;

    if (!(o instanceof Person))
      return false;

    Person person = (Person)o;
    return id == person.id && name.equals(person.name);
  }
}
