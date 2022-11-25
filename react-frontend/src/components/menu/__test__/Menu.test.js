import '@testing-library/jest-dom'
import { render, screen, cleanup } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import Menu from '../Menu';

const MockMenu = () => {
    return(
        <BrowserRouter>
            <Menu/>
        </BrowserRouter>
    )
}

describe("Menu unit tests", () => {
    
    afterEach(()=>{
        cleanup();
    })


    it('should display the Home link', async() => {
        render(<MockMenu/>)
        const MenuLinkElement = screen.getByText(/Hello/i);
        expect(MenuLinkElement).toBeInTheDocument();
    })


    it('should display the Add Blog link', async() => {
        render(<MockMenu/>)
        const MenuLinkElement = screen.getByText(/Hello/i);
        expect(MenuLinkElement).toBeInTheDocument();
    })
    
})