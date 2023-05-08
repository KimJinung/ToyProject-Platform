package kimjinung.platform.domain.item;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CategoryTest {

    @Test
    void testAddChild() {
        Category parent = new Category("parent");

        int childSize = 10;
        for (int i = 0; i < childSize; i++) {
            Category child = new Category("child" + i, parent);
            parent.addChild(child);
        }

        for (Category child : parent.getChild()) {
            assertThat(child.getParent().getName()).isEqualTo(parent.getName());
        }

        assertThat(parent.getChild().size()).isEqualTo(10);
    }

}