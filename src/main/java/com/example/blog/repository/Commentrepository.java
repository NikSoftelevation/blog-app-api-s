package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entities.Comment;

public interface Commentrepository extends JpaRepository<Comment, Integer> {

}
