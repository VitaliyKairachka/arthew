INSERT INTO public.regions(name, country_id, place_count, hotel_count)
SELECT 'region-' || num, 1, 5, 25
FROM generate_series(1, 2) num;