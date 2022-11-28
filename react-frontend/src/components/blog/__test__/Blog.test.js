import '@testing-library/jest-dom'
import { render, screen, cleanup } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import Blog from '../Blog';
import blogData from './testBlog.json';

const MockBlog = ({blogData}) => {
    return (
        <BrowserRouter>
            <Blog blog={blogData}/>
        </BrowserRouter>
    )
}

describe("Blog unit tests", () => {
    
    // beforeEach(() => {
    //     render(<MockBlog blogData={blogData}/>)
    // })

    afterEach(()=>{
        cleanup();
    })

    it('should render the Card Title Element with the title of the Blog', async() => {
        render(<MockBlog blogData={blogData}/>)
        const CardTitleElement = screen.getByText(/New Blog/i);
        expect(CardTitleElement).toBeInTheDocument();

    })

    it('should render the Card Text Content Element with the content of the Blog', async() => {
        render(<MockBlog blogData={blogData}/>)
        const CardTextContentElement = screen.getByText(/blah blah blah/i);
        expect(CardTextContentElement).toBeInTheDocument();

    })

    it('should render the Edit link', async() => {
        render(<MockBlog blogData={blogData}/>)
        const EditLinkElement = screen.getByText(/Edit/i);
        expect(EditLinkElement).toBeInTheDocument();
        expect(EditLinkElement).toHaveAttribute('href')
    })

    it('should render the Delete Button', async() => {
        render(<MockBlog blogData={blogData}/>)
        const DeleteButtonElement = screen.getByRole('button', {name: /Delete/i});
        expect(DeleteButtonElement).toBeInTheDocument();
    })



})