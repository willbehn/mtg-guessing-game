import { useState } from 'react'
import './App.css'
import HintList from './HintList'

export default function App() {
  const [count, setCount] = useState(0)
  

  const handleClick = () => {

  }

  return (
    <>
    <div>
      <HintList></HintList>
    </div>
    </>
  )
}

