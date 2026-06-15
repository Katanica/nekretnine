import { createContext, useContext } from "react";
import useBookmarks from "../hooks/useBookmarks";

export const BookmarksContext = createContext(null);

export function BookmarksProvider({ children }) {
  const value = useBookmarks();
  return (
    <BookmarksContext.Provider value={value}>
      {children}
    </BookmarksContext.Provider>
  );
}

export function useBookmarksContext() {
  return useContext(BookmarksContext);
}
