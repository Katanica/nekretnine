import { supabase } from './supabase'

const ALLOWED_TYPES = ['image/jpeg', 'image/png', 'image/webp', 'image/jpg']
const MAX_SIZE = 5 * 1024 * 1024

const MIME_TO_EXT = {
    'image/jpeg': 'jpg',
    'image/png': 'png',
    'image/webp': 'webp'
}

export async function uploadImage(file) {
    if (!ALLOWED_TYPES.includes(file.type)) {
        throw new Error('Dozvoljeni formati: JPG, PNG, WEBP')
    }
    if (file.size > MAX_SIZE) {
        throw new Error('Maksimalna veličina je 5MB')
    }

    const extension = MIME_TO_EXT[file.type]
    const fileName = `${crypto.randomUUID()}.${extension}`

    const { error } = await supabase.storage
        .from('images')
        .upload(fileName, file)

    if (error) throw error

    const { data } = supabase.storage
        .from('images')
        .getPublicUrl(fileName)

    return data.publicUrl
}