import { css, Global } from '@emotion/react'
import React from 'react'
import type { ThemeType } from './theme'

export const GlobalStyles = ({ theme }: { theme: ThemeType }) => (
  <Global 
    styles={css`
      * {
        box-sizing: border-box;
        transition: background 0.25s, color 0.25s;
      }
      body {
        margin: 0;
        padding: 0;
        font-family: Verdana, Geneva, Tahoma, sans-serif;
        background:${theme.colors.background};
        color:${theme.colors.text}
      }
    `}
  />
)