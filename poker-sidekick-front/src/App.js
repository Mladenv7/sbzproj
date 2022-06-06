import NewTableStatePage from './pages/NewTableStatePage';
import Layout from './components/ui/Layout';
import { Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<NewTableStatePage />} />
      </Routes>
    </Layout>
  );
}

export default App;
