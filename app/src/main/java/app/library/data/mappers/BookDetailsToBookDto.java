package app.library.data.mappers;

import app.library.network.dto.BookDetailsDto;
import app.library.network.dto.BookDto;

public final class BookDetailsToBookDto {
    public static BookDto map(BookDetailsDto dto) {
        return new BookDto(
                dto.getImage(), dto.getPrice(), dto.getSubtitle(), dto.getIsbn13(), dto.getTitle(), dto.getUrl()
        );
    }
}
