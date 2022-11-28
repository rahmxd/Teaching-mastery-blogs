import '@testing-library/jest-dom'
import { render, screen, cleanup } from '@testing-library/react';
//import { BrowserRouter } from 'react-router-dom';
import App from "./App";

describe("App unit tests", () => {
    
    afterEach(()=>{
        cleanup();
    })

    xit('should render the Card Title Element with the title of the Blog', async() => {
        render(<App />)
        const CardTitleElement = screen.getByText(/Teaching Mastery Blog: CPD/i);
        expect(CardTitleElement).toBeInTheDocument();

    })



})