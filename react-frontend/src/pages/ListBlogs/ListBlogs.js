import React, { useEffect, useState } from 'react'
import { Card, CardTitle } from 'reactstrap'
import Blog from '../../components/blog/Blog';
// import axios from 'axios';

const ListBlogs = ({blogs}) => {

  const generateBlogs = blogs?.map((blog) => {
    return <Blog key={blog.blogID} blog={blog} />
  })

  return (
    <Card body inverse color='info'>
        <CardTitle className='display-2'>
            All Blogs
        </CardTitle>
        {
          generateBlogs
        }
    </Card>
  )
}

export default ListBlogs