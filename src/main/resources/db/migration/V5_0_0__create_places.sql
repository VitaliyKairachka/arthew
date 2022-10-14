INSERT INTO public.places(name, region_id, hotel_count, photo_count)
SELECT 'place-' || num, 1, 5, 1
FROM generate_series(1, 5) num;

INSERT INTO public.places(name, region_id, hotel_count, photo_count)
SELECT 'place-' || num, 2, 5, 1
FROM generate_series(6, 10) num;