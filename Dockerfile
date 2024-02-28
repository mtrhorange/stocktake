FROM jhipster/jhipster
WORKDIR /app
COPY . /app
RUN mvn
CMD ["mvn"]