import { useState } from 'react'
import './App.css'
import { ThemeProvider } from '@emotion/react'
import { GlobalStyles } from './styles/Global'
import { darkTheme, lightTheme } from './styles/theme';

function App() {
  const [isDark, setIsDark] = useState<boolean>(false);
  const toggleTheme = () => setIsDark(prev => !prev);

  const theme = isDark ? darkTheme : lightTheme;

  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles theme={theme}/>
    </ThemeProvider>
  )
}

export default App
