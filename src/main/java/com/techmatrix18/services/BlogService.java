package com.techmatrix18.services;

import com.techmatrix18.model.Blog;
import com.techmatrix18.repositories.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is BlogService
 *
 * @company for TechMatrix18
 * @author Alexander Kuziv
 * @since 01.06.205
 * @version 0.0.1
 */

@Service
public class BlogService {

    private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    /**
     * List of all posts from Space ID
     *
     * @param spaceId
     * @return
     */
    public List<Blog> getAllPosts(Long spaceId) {
        return blogRepository.findBySpaceIdOrderBySectorAsc(spaceId);
    }

    /**
     * Lats 10 posts of Blog from Space ID
     *
     * @param spaceId
     * @return
     */
    public List<Blog> getLast10Posts(Long spaceId) {
        return blogRepository.findTop2BySpaceIdOrderByCreatedAtAsc(spaceId);
    }

    // Sector la sorpresa =)

    /**
     * List of posts by SpaceID and sector
     *
     * @param spaceId
     * @param sector
     * @return
     */
    public List<Blog> getAllPostsBySector(Long spaceId, Integer sector) {
        return blogRepository.findBySpaceIdAndSector(spaceId, sector);
    }

    /**
     * Add Map
     *
     * @return boolean
     */
    public boolean addPost(Blog blog) {
        Blog b = blogRepository.save(blog);
        if (b.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edit Map
     *
     * @return boolean
     */
    public boolean updatePost(Blog blog) {
        Blog b = blogRepository.save(blog);
        if (b.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete Map by MapID
     *
     * @return boolean
     */
    public boolean deletePost(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.get().getId() != null) {
            blogRepository.delete(blog.get());
            return true;
        } else {
            return false;
        }
    }
}

