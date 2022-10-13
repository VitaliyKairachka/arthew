INSERT INTO public.numbers(description, hotel_id, photo_count)
SELECT 'best number!', num2, 1
FROM generate_series(1, 200000) num,
     generate_series(1, 5) num2;