import { Outlet } from "react-router-dom";
import NavBar from "../components/NavBar.jsx";

export default function RootPage() {
  return (
    <>
      <NavBar />
      <Outlet />
    </>
  );
}
