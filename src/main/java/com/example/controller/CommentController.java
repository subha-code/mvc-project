package com.example.controller;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public ResponseEntity<Comment> createStatement(
            @RequestBody Comment comment,
            @RequestParam long postId
    ){
        Post post = postRepository.findById(postId).get();
        comment.setPost(post); // set postId object in comment entity as a foreign key
        return ResponseEntity.ok(commentRepository.save(comment));


    }
}
