INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 1, 200000, 1
FROM generate_series(1, 5) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 2, 200000, 1
FROM generate_series(6, 10) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 3, 200000, 1
FROM generate_series(11, 15) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 4, 200000, 1
FROM generate_series(16, 20) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 5, 200000, 1
FROM generate_series(21, 25) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 6, 200000, 1
FROM generate_series(26, 30) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 7, 200000, 1
FROM generate_series(31, 35) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 8, 200000, 1
FROM generate_series(36, 40) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 9, 200000, 1
FROM generate_series(41, 45) num;

INSERT INTO public.hotels(name, place_id, number_count, photo_count)
SELECT 'hotel-' || num, 10, 200000, 1
FROM generate_series(46, 50) num;