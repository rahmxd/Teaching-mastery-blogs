import '@testing-library/jest-dom'
import { render, screen, cleanup } from '@testing-library/react';
import ListBlogs from '../ListBlogs';
// import testBlogs from './testBlogs.json';
import { BrowserRouter } from 'react-router-dom';

const MockListBlogs = () => {
    return(
        <BrowserRouter>
            <ListBlogs/>
        </BrowserRouter>
    )
}

describe("ListBlogs unit tests", () => {
    
    afterEach(()=>{
        cleanup();
    })

    it('should render the Card Title Element with the title of the Blog', async() => {
        render(<MockListBlogs/>);
        const CardTitleElement = screen.getByText(/All Blogs/i);
        expect(CardTitleElement).toBeInTheDocument();

    })

    it('should render the test Blog data', async() => {
        const testBlogs = 
        [
            {
                "blogID": "12345abcde",
                "title": "Welcome to teaching Mastery Maths",
                "content": "blah blah blah"
            },
            {
                "blogID": "12345abcde",
                "title": "Welcome to teaching Mastery Maths",
                "content": "blah blah blah"
            }

        ];
        
        render(<MockListBlogs blogs={testBlogs}/>);
        const CardTitleElement = screen.getByText(/Welcome to teaching Mastery Maths/i);
        expect(CardTitleElement).toBeInTheDocument();

    })

})