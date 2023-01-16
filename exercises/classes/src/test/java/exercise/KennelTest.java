package exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @BeforeEach
    void clearStaticVariable() throws Exception {
        Kennel.resetKennel();
    }

    @Test
    void testKennel() {
        Kennel.addPuppy(new String[] {"Rex", "boxer"});
        assertThat(Kennel.getPuppyCount()).isEqualTo(1);

        String[][] puppies = {
            {"Rocky", "terrier"},
            {"Buddy", "chihuahua"},
            {"Toby", "chihuahua"},
        };

        Kennel.addSomePuppies(puppies);
        assertThat(Kennel.getPuppyCount()).isEqualTo(4);
        assertThat(Kennel.isContainPuppy("Buddy")).isTrue();
        assertThat(Kennel.isContainPuppy("Olson")).isFalse();

        String[][] actual1 = Kennel.getAllPuppies();
        String[][] expected1 = {
            {"Rex", "boxer"},
            {"Rocky", "terrier"},
            {"Buddy", "chihuahua"},
            {"Toby", "chihuahua"},
        };
        assertThat(actual1).isDeepEqualTo(expected1);

        String[] actual2 = Kennel.getNamesByBreed("chihuahua");
        assertThat(actual2).containsOnly("Buddy", "Toby");

        Kennel.resetKennel();
        assertThat(Kennel.getPuppyCount()).isEqualTo(0);
    }

    // BEGIN
    @Test
    void testRemovePuppy() {
        String[][] puppies = {
                {"Rex", "boxer"},
                {"Rocky", "terrier"},
                {"Buddy", "chihuahua"},
                {"Toby", "chihuahua"}
        };
        Kennel.addSomePuppies(puppies);
        assertThat(Kennel.removePuppy("Rex1")).isFalse();
        assertThat(Kennel.removePuppy("Rex")).isTrue();
        String[][] actual = Kennel.getAllPuppies();

        String[][] expected = {
                {"Rocky", "terrier"},
                {"Buddy", "chihuahua"},
                {"Toby", "chihuahua"}
        };
        assertThat(actual).isDeepEqualTo(expected);
        assertThat(Kennel.removePuppy("Toby")).isTrue();
        String[][] expected1 = {
                {"Rocky", "terrier"},
                {"Buddy", "chihuahua"}
        };
        String[][] actual1 = Kennel.getAllPuppies();
        assertThat(actual1).isDeepEqualTo(expected1);

        assertThat(Kennel.removePuppy("Rocky")).isTrue();
        assertThat(Kennel.removePuppy("Buddy")).isTrue();
        String[][] expected2 = {};
        String[][] actual2 = Kennel.getAllPuppies();
        assertThat(actual2).isDeepEqualTo(expected2);
        assertThat(Kennel.removePuppy("Buddy")).isFalse();
    }
    // END
}
