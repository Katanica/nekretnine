import { useBookmarksContext } from "../context/BookmarksContext";
import AdvertDetailsModal from "./AdvertDetailsModal";

export default function BookmarkAdvertModal() {
  const { openModalAdvertId, closeAdvertModal } = useBookmarksContext();
  if (!openModalAdvertId) return null;
  return (
    <AdvertDetailsModal advert={openModalAdvertId} onClose={closeAdvertModal} />
  );
}
