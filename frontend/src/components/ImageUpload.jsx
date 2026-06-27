import { useState } from 'react'
import { uploadImage } from '../uploadImage'

export default function ImageUpload({ onChange }) {
    const [previews, setPreviews] = useState([])
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState(null)

    const handleFiles = async (e) => {
        const files = Array.from(e.target.files)
        if (!files.length) return

        setLoading(true)
        setError(null)

        try {
            const urls = await Promise.all(files.map(uploadImage))
            const newPreviews = files.map((file, i) => ({
                url: urls[i],
                preview: URL.createObjectURL(file)
            }))
            setPreviews(prev => {
                const updated = [...prev, ...newPreviews]
                onChange(updated.map(p => p.url))
                return updated
            })
        } catch (err) {
            setError(err.message)
        } finally {
            setLoading(false)
        }
    }

    const remove = (index) => {
        setPreviews(prev => {
            const updated = prev.filter((_, i) => i !== index)
            onChange(updated.map(p => p.url))
            return updated
        })
    }

    return (
        <div>
            <input
                type="file"
                accept="image/jpeg,image/png,image/webp"
                multiple
                onChange={handleFiles}
                disabled={loading}
            />

            {error && <p style={{ color: 'red' }}>{error}</p>}
            {loading && <p>Objavljivanje...</p>}

            <div style={{ display: 'flex', gap: 8, flexWrap: 'wrap', marginTop: 8 }}>
                {previews.map((p, i) => (
                    <div key={i} style={{ position: 'relative' }}>
                        <img
                            src={p.preview}
                            alt=""
                            style={{ width: 100, height: 100, objectFit: 'cover', borderRadius: 4 }}
                        />
                        <button
                            onClick={() => remove(i)}
                            style={{
                                position: 'absolute', top: 2, right: 2,
                                background: 'rgba(0,0,0,0.6)', color: '#fff',
                                border: 'none', borderRadius: '50%',
                                width: 20, height: 20, cursor: 'pointer', fontSize: 12
                            }}
                        >
                            ×
                        </button>
                    </div>
                ))}
            </div>
        </div>
    )
}