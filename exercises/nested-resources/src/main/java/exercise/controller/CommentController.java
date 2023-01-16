package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping("/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId_Alt(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getCommentById(@PathVariable long postId,
                                  @PathVariable long commentId) {

        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment" + commentId + "not found"));
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity postComment(@PathVariable long postId,
                                         @RequestBody Comment comment) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
            comment.setPost(post);
            commentRepository.save(comment);
            return ResponseEntity.ok(commentRepository.findAllByPostId(postId));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<String>("Post " + postId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public Comment patchCommentById(@PathVariable long postId,
                                    @PathVariable long commentId,
                                    @RequestBody Comment updateComment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post" + postId + "not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment" + commentId + " not found"));
        comment.setContent(updateComment.getContent());
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteCommentById(@PathVariable long postId,
                                  @PathVariable long commentId) {

        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post" + postId + "not found");
        }
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment" + commentId + " not found"));

        commentRepository.delete(comment);
    }
    // END
}
