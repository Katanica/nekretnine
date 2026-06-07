import Register, { action as registerAction } from "./page/Register";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import HomePage from "./page/Home";
import RootPage from "./page/Root";
import Login, { action as loginAction } from "./page/Login";
import AddAdvert, { action as addAdvertAction } from "./page/AddAdvert";
import Profile from "./page/Profile";
import { action as logoutAction } from "./components/Logout";
import { checkAuthLoader, tokenLoader } from "./api";
import EditProfile from "./page/EditProfile";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootPage />,
    loader: tokenLoader,
    children: [
      { index: true, element: <HomePage /> },
      { path: "register", element: <Register />, action: registerAction },
      { path: "login", element: <Login />, action: loginAction },
      { path: "add-advert", element: <AddAdvert />, action: addAdvertAction },
      {
        path: "profile/:id",
        element: <Profile />,
        children: [{ path: "edit", element: <EditProfile /> }],
      },
      { path: "logout", action: logoutAction },
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
