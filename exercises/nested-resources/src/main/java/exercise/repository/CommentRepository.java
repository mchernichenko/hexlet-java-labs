package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    Iterable<Comment> findAllByPostId(long postId);

    // в автогенерилке оказывается важен порядок перечисления параметров :o
    // порядок согласно имени метода, т.е. сначала id коммента, затем id поста
    Optional<Comment> findByIdAndPostId(long commentId, long postId);
    // END

    // Альтернативные варианты запросов, без использования автогенерилки

    // с использованием нативного SQL
    //@Query(value = "SELECT * FROM comments WHERE post_id = :postId", nativeQuery = true)
    // с использованием запрос на HQL => важно, что Comment-класс, а не таблица.
    @Query("select c from Comment c where c.post.id = :postId")
    Iterable<Comment> findAllByPostId_Alt(@Param("postId") long postId);

    @Query("select c from Comment c where c.post.id = :postId and c.id = :commentId")
    Optional<Comment> findByIdAndPostId_Alt(@Param("postId") long postId,
                                  @Param("commentId") long commentId );
}
