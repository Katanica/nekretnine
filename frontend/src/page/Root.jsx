import { Outlet } from "react-router-dom";
import NavBar from "../components/NavBar.jsx";
import { BookmarksProvider } from "../context/BookmarksContext.jsx";
import BookmarksSidebar from "../components/Bookmarks.jsx";
import BookmarkAdvertModal from "../components/BookmarkAdvertModal.jsx";

export default function RootPage() {
  return (
    <BookmarksProvider>
      <NavBar />
      <Outlet />
      <BookmarksSidebar />
      <BookmarkAdvertModal />
    </BookmarksProvider>
  );
}
