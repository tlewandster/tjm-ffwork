package pl.tlewandster.ffwork.domain;

public class UserTest {
    static void main() {
        User user1 = new IndividualUser("kunefal@op.pl","Jaś Kunefał", "12345678901");
        User user2 = new CompanyUser("xxx@yyy.zz","Cośtam Kąpany", "1234567890");
    }
}
