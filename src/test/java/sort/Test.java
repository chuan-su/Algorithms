package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

  class Hit {
    int rank;
  }

  class Name {
    String firstName;
    String lastName;

    Name(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }
  }

  class Match {
    int matchedPosition;
    int editDistance;
  }

  @org.junit.Test
  public void test() {
//    String[] query = {"iste", "nik"};
//    List<Name> names = List.of(new Name("Niklas Tony", "Istenes"), new Name("kNiklas Tony", "Istenes"), new Name("Chuan niklas", "istenes"));

    String[] query = {"ander"};
    List<Name> names = List.of(new Name("anders", "andersson"), new Name("anders", "anderhanson"), new Name("Chuan niklas", "istenes"));

    FILTER_AND_RANK: for (Name name : names) {
      String[] tokens = Stream
        .of(name.firstName, name.lastName)
        .flatMap(token -> Pattern.compile(" ").splitAsStream(token))
        .map(s -> s.toLowerCase(Locale.getDefault()))
        .toArray(String[]::new);

      int[][] matrix = new int[query.length][tokens.length];

      int[] minIdxes = new int[query.length];
      int maxLength = name.firstName.length() + name.lastName.length();
      Arrays.fill(minIdxes, maxLength);

      for (int i = 0; i < matrix.length; i ++) {
        boolean matched = false;
        for (int j = 0; j < matrix[i].length; j++) {
          int matchPosition = tokens[j].indexOf(query[i]);
          if (matchPosition >= 0) {
            matched = true;
          }
          matrix[i][j] = matchPosition;
        }
        if (!matched) {
          continue FILTER_AND_RANK;
        }
      }

      Match[] matchedMinPostions = new Match[tokens.length];

      for (int j = 0; j < tokens.length; j ++) {
        for (int i = 0; i < query.length; i ++) {
          int mp = matrix[i][j];
          Match m = new Match();
          m.matchedPosition = mp;
          m.editDistance = mp >= 0 ? tokens[j].length() - query[i].length() : tokens[j].length();
          Comparator<Match> closeMath = (a, b) -> {
            if (a.matchedPosition == -1 && b.matchedPosition == -1) {
              return a.editDistance - b.editDistance;
             } else if (a.matchedPosition == -1) {
              return 1;
            } else if (b.matchedPosition == -1) {
              return -1;
            } else if (a.matchedPosition == 0 && b.matchedPosition == 0) {
              return a.editDistance - b.editDistance;
            } else if (a.matchedPosition == 0){
              return -1;
            } else if (b.matchedPosition == 0) {
              return 1;
            } else {
              return a.editDistance - b.editDistance;
            }
          };
          if (matchedMinPostions[j] == null || closeMath.compare(m, matchedMinPostions[j]) <= 0) {
            matchedMinPostions[j] = m;
          }
        }
      }
      int rank = IntStream.rangeClosed(1, tokens.length).reduce(0, Integer::sum);
      int editDistance = 0;
      for (int i = 0; i < matchedMinPostions.length; i ++) {
        if (matchedMinPostions[i].matchedPosition == 0) {
          rank -= (matchedMinPostions.length - i);
        }
        editDistance += matchedMinPostions[i].editDistance;
      }
      System.out.println("rank=" + rank + " editDistance=" + editDistance + " name="+name.firstName + " " + name.lastName);
    }
  }
}
