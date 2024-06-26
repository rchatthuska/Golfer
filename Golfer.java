public class Golfer<T extends Number> implements Comparable<Golfer<T>> {
   private String lastName;
   private int numberOfRounds;
   private T averageScore;

   public Golfer() {
      this.lastName = "";
      this.numberOfRounds = 0;
      this.averageScore = null;
   }

   public Golfer(String last) {
      this.lastName = last;
      this.numberOfRounds = 0;
      this.averageScore = null;
   }

   public Golfer(String name, int number, T score) {
      this.lastName = name;
      this.numberOfRounds = number;
      this.averageScore = score;
   }

   public String getLastName() {
      return this.lastName;
   }

   public int getNumberOfRounds() {
      return this.numberOfRounds;
   }

   public T getAverageScore() {
      return this.averageScore;
   }

   public void setLastName(String name) {
      this.lastName = name;
   }

   public void setNumberOfRounds(int number) {
      this.numberOfRounds = number;
   }

   public void setAverageScore(T score) {
      this.averageScore = score;
   }

   public void addScore(T newScore) {
      double totalScore = this.averageScore.doubleValue() * (double)this.numberOfRounds;
      totalScore += newScore.doubleValue();
      ++this.numberOfRounds;
      this.averageScore = totalScore / (double)this.numberOfRounds;
   }

   public int compareTo(Golfer<T> other) {
      int lastNameComparison = this.getLastName().compareTo(other.getLastName());
      if (lastNameComparison == 0) {
         return 0;
      } else {
         return lastNameComparison < 0 ? -1 : 1;
      }
   }

   public String toString() {
      return this.lastName + " " + this.numberOfRounds + " " + this.averageScore;
   }
}
