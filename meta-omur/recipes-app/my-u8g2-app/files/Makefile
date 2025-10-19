TARGET = my_u8g2_app

all: ${TARGET}

${TARGET}: my_u8g2_app.c
	  ${CC} ${CFLAGS} ${LDFLAGS} -o ${TARGET} my_u8g2_app.c -lu8g2


clean:
	rm -f ${TARGET}

install: all
    install -d ${DESTDIR}$(bindir)
    install -m 0755 ${TARGET} ${DESTDIR}$(bindir)/${TARGET}
