package kimjinung.platform.domain.base;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class BaseEntityTest {

    @Test
    void testBaseEntity() {
        BaseEntity baseEntity = new BaseEntity();

        LocalDateTime createdAt = baseEntity.getCreatedAt();
        LocalDateTime updatedAt = baseEntity.getUpdatedAt();

        assertThat(createdAt).isExactlyInstanceOf(LocalDateTime.class);
        assertThat(updatedAt).isExactlyInstanceOf(LocalDateTime.class);

        assertThat(createdAt).isNotNull();
        assertThat(updatedAt).isNotNull();
    }

}