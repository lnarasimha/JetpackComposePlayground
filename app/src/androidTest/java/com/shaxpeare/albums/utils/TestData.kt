package com.shaxpeare.albums.utils

import com.shaxpeare.albums.data.model.ApiAlbum
import com.shaxpeare.albums.data.model.ApiPhoto
import com.shaxpeare.albums.data.model.ApiUser
import com.shaxpeare.albums.domain.model.Album
import com.shaxpeare.albums.domain.model.Photo
import com.shaxpeare.albums.domain.model.User

object TestData {

    val ALBUM_ITEM_INVALID_1 = ApiAlbum(
        id = -1,
        userId = -1,
        title = ""
    )

    val ALBUM_ITEM_VALID_1 = ApiAlbum(
        id = 1,
        userId = 1,
        title = "quidem molestiae enim"
    )

    val ALBUM_ITEM_VALID_1_DOMAIN = Album(
        id = 1,
        userId = 1,
        title = "quidem molestiae enim"
    )

    val ALBUM_ITEM_VALID_2 = ApiAlbum(
        id = 11,
        userId = 2,
        title = "quam nostrum impedit mollitia quod et dolor"
    )

    val ALBUM_ITEM_VALID_2_DOMAIN = Album(
        id = 11,
        userId = 2,
        title = "quam nostrum impedit mollitia quod et dolor"
    )

    val ALBUM_ITEM_VALID_3 = ApiAlbum(
        id = 21,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_3_DOMAIN = Album(
        id = 21,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_4 = ApiAlbum(
        id = 22,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_5 = ApiAlbum(
        id = 23,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_6 = ApiAlbum(
        id = 24,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_7 = ApiAlbum(
        id = 25,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_8 = ApiAlbum(
        id = 26,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_9 = ApiAlbum(
        id = 27,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_10 = ApiAlbum(
        id = 28,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_11 = ApiAlbum(
        id = 29,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_12 = ApiAlbum(
        id = 30,
        userId = 3,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_13 = ApiAlbum(
        id = 31,
        userId = 4,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_14 = ApiAlbum(
        id = 32,
        userId = 4,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_15 = ApiAlbum(
        id = 33,
        userId = 4,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_16 = ApiAlbum(
        id = 34,
        userId = 4,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val ALBUM_ITEM_VALID_17 = ApiAlbum(
        id = 35,
        userId = 4,
        title = "repudiandae voluptatem optio est consequatur rem in temporibus et"
    )

    val PHOTO_ITEM_INVALID_1 = ApiPhoto(
        id = -1,
        albumId = -1,
        title = "accusamus beatae ad facilis cum similique qui sunt",
        url = "https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https://via.placeholder.com/150/92c952"
    )

    val PHOTO_ITEM_VALID_1 = ApiPhoto(
        id = 1,
        albumId = 1,
        title = "accusamus beatae ad facilis cum similique qui sunt",
        url = "https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https://via.placeholder.com/150/92c952"
    )

    val PHOTO_ITEM_VALID_1_DOMAIN = Photo(
        id = 1,
        albumId = 1,
        title = "accusamus beatae ad facilis cum similique qui sunt",
        url = "https://via.placeholder.com/600/92c952",
        thumbnailUrl = "https://via.placeholder.com/150/92c952"
    )

    val PHOTO_ITEM_VALID_2 = ApiPhoto(
        id = 2,
        albumId = 1,
        title = "reprehenderit est deserunt velit ipsam",
        url = "https://via.placeholder.com/600/771796",
        thumbnailUrl = "https://via.placeholder.com/150/771796"
    )

    val PHOTO_ITEM_VALID_2_DOMAIN = Photo(
        id = 2,
        albumId = 1,
        title = "reprehenderit est deserunt velit ipsam",
        url = "https://via.placeholder.com/600/771796",
        thumbnailUrl = "https://via.placeholder.com/150/771796"
    )

    val PHOTO_ITEM_VALID_3 = ApiPhoto(
        id = 51,
        albumId = 2,
        title = "non sunt voluptatem placeat consequuntur rem incidunt",
        url = "https://via.placeholder.com/600/8e973b",
        thumbnailUrl = "https://via.placeholder.com/150/8e973b"
    )

    val PHOTO_ITEM_VALID_3_DOMAIN = Photo(
        id = 51,
        albumId = 2,
        title = "non sunt voluptatem placeat consequuntur rem incidunt",
        url = "https://via.placeholder.com/600/8e973b",
        thumbnailUrl = "https://via.placeholder.com/150/8e973b"
    )

    val PHOTO_ITEM_VALID_4 = ApiPhoto(
        id = 52,
        albumId = 2,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )



    val PHOTO_ITEM_VALID_4_DOMAIN = Photo(
        id = 52,
        albumId = 2,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_5 = ApiPhoto(
        id = 101,
        albumId = 3,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_6 = ApiPhoto(
        id = 102,
        albumId = 3,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_7 = ApiPhoto(
        id = 151,
        albumId = 4,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_8 = ApiPhoto(
        id = 152,
        albumId = 4,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_9 = ApiPhoto(
        id = 201,
        albumId = 5,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_10 = ApiPhoto(
        id = 202,
        albumId = 5,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_11 = ApiPhoto(
        id = 251,
        albumId = 6,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_12 = ApiPhoto(
        id = 252,
        albumId = 6,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_13 = ApiPhoto(
        id = 301,
        albumId = 7,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val PHOTO_ITEM_VALID_14 = ApiPhoto(
        id = 302,
        albumId = 7,
        title = "eveniet pariatur quia nobis reiciendis laboriosam ea",
        url = "https://via.placeholder.com/600/121fa4",
        thumbnailUrl = "https://via.placeholder.com/150/121fa4"
    )

    val USER_ITEM_INVALID_1 = ApiUser(
        id = -1,
        name = "",
        username = "",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
    )

    val USER_ITEM_VALID_1 = ApiUser(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
    )

    val USER_ITEM_VALID_1_DOMAIN = User(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
    )

    val USER_ITEM_VALID_2 = ApiUser(
        id = 2,
        name = "Ervin Howell",
        username = "Antonette",
        email = "Shanna@melissa.tv",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
    )

    val USER_ITEM_VALID_2_DOMAIN = User(
        id = 2,
        name = "Ervin Howell",
        username = "Antonette",
        email = "Shanna@melissa.tv",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
    )

    val USER_ITEM_VALID_3 = ApiUser(
        id = 3,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )

    val USER_ITEM_VALID_3_DOMAIN = User(
        id = 3,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )

    val USER_ITEM_VALID_4 = ApiUser(
        id = 4,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )

    val USER_ITEM_VALID_5 = ApiUser(
        id = 5,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )

    val USER_ITEM_VALID_6 = ApiUser(
        id = 6,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )

    val USER_ITEM_VALID_7 = ApiUser(
        id = 7,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )

    val USER_ITEM_VALID_8 = ApiUser(
        id = 8,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )

    val USER_ITEM_VALID_9 = ApiUser(
        id = 9,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )

    val USER_ITEM_VALID_10 = ApiUser(
        id = 10,
        name = "Clementine Bauch",
        username = "Samantha",
        email = "Nathan@yesenia.net",
        phone = "1-463-123-4447",
        website = "ramiro.info",
    )
}
