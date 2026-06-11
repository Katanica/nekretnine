import Register, { action as registerAction } from "./page/Register";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import HomePage from "./page/Home";
import RootPage from "./page/Root";
import About from "./page/About";
import Contact from "./page/Contact";
import Login, { action as loginAction } from "./page/Login";
import AddAdvert, { action as addAdvertAction } from "./page/AddAdvert";
import Profile from "./page/Profile";
import { action as logoutAction } from "./components/Logout";
import { checkAuthLoader, tokenLoader } from "./api";
import ErrorPage from "./page/ErrorPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootPage />,
    loader: tokenLoader,
    children: [
      { index: true, element: <HomePage /> },
      {
        path: "register",
        element: <Register />,
        action: registerAction,
        errorElement: <ErrorPage />,
      },
      {
        path: "login",
        element: <Login />,
        action: loginAction,
        errorElement: <ErrorPage />,
      },
      { path: "about-us", element: <About /> },
      { path: "contact", element: <Contact /> },
      { path: "home", element: <HomePage /> },

      {
        path: "add-advert",
        element: <AddAdvert />,
        action: addAdvertAction,
        errorElement: <ErrorPage />,
      },
      {
        path: "profile/:id",
        element: <Profile />,
      },
      { path: "logout", action: logoutAction },
      { path: "*", element: <ErrorPage /> },
    ],
  },
]);
function App() {
  return (
    <>
      <RouterProvider router={router} />
    </>
  );
}

export default App;
