import { useState, useEffect, useCallback } from "react";
import { getToken, getUserID } from "../api";

const BASE_URL = "http://localhost:8080";

export default function useBookmarks() {
  const [bookmarks, setBookmarks] = useState([]);
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [openModalAdvertId, setOpenModalAdvertId] = useState(null);

  useEffect(() => {
    const token = getToken();
    const profileId = getUserID();
    if (!profileId || !token || token === "EXPIRED") return;

    fetch(`${BASE_URL}/api/bookmark/profile/${profileId}`, {
      headers: { Authorization: `Bearer ${token}` },
    })
      .then((res) => (res.ok ? res.json() : []))
      .then(setBookmarks)
      .catch(console.error);
  }, []);

  const addBookmark = useCallback(async (advertId) => {
    const token = getToken();
    const profileId = getUserID();
    if (!token || token === "EXPIRED" || !profileId) return;

    try {
      const res = await fetch(
        `${BASE_URL}/api/bookmark?advertId=${advertId}&profileId=${profileId}`,
        { method: "POST", headers: { Authorization: `Bearer ${token}` } },
      );
      if (res.ok) {
        const newBookmark = await res.json();
        setBookmarks((prev) => [...prev, newBookmark]);
      }
    } catch (err) {
      console.error(err);
    }
  }, []);

  const removeBookmark = useCallback(async (advertId) => {
    const token = getToken();
    const profileId = getUserID();
    if (!token || token === "EXPIRED" || !profileId) return;

    try {
      const res = await fetch(
        `${BASE_URL}/api/bookmark?advertId=${advertId}&profileId=${profileId}`,
        { method: "DELETE", headers: { Authorization: `Bearer ${token}` } },
      );
      if (res.ok) {
        setBookmarks((prev) =>
          prev.filter((b) => {
            const bAdvertId = b.advertId ?? b.advert?.id ?? b.id;
            return bAdvertId !== advertId;
          }),
        );
      }
    } catch (err) {
      console.error(err);
    }
  }, []);

  const isBookmarked = useCallback(
    (advertId) =>
      bookmarks.some((b) => {
        const bAdvertId = b.advertId ?? b.advert?.id ?? b.id;
        return bAdvertId === advertId;
      }),
    [bookmarks],
  );

  return {
    bookmarks,
    bookmarkCount: bookmarks.length,
    addBookmark,
    removeBookmark,
    isBookmarked,
    sidebarOpen,
    openSidebar: () => setSidebarOpen(true),
    closeSidebar: () => setSidebarOpen(false),
    openModalAdvertId,
    openAdvertModal: (advert) => setOpenModalAdvertId(advert),
    closeAdvertModal: () => setOpenModalAdvertId(null),
  };
}
